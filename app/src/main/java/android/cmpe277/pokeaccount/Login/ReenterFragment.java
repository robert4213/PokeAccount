package android.cmpe277.pokeaccount.Login;

import android.cmpe277.pokeaccount.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.security.auth.x500.X500Principal;

public class ReenterFragment extends LoginBasicFragment {
    public static final String TAG = "KeyStoreFragment";
    String passcode;

    public ReenterFragment(String passcode){
        this.passcode = passcode;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        final TextView loginText = view.findViewById(R.id.text_login);
        TextView loginTextBtn = view.findViewById(R.id.text_login_btn);

        loginText.setText("Please re-enter your passcode");
        loginTextBtn.setText("Cancel");

        loginTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_login, new EnterFragment()).commit();
            }
        });

        getPinView().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginText.setText("Please re-enter your passcode");
                loginText.setTextColor(getResources().getColor(R.color.quantum_white_100));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == LENGTH){
                    if(charSequence.toString().equals(passcode)){
                        try {
                            createKeys(getActivity());
                            Log.d(TAG, "Keys created");
                        } catch (NoSuchAlgorithmException e) {
                            Log.w(TAG, "RSA not supported", e);
                        } catch (InvalidAlgorithmParameterException e) {
                            Log.w(TAG, "No such provider: AndroidKeyStore");
                        } catch (NoSuchProviderException e) {
                            Log.w(TAG, "Invalid Algorithm Parameter Exception", e);
                        }
                        String mSignatureStr = "";
                        try {
                            mSignatureStr = signData(passcode);


                        } catch (KeyStoreException e) {
                            Log.w(TAG, "KeyStore not Initialized", e);
                        } catch (UnrecoverableEntryException e) {
                            Log.w(TAG, "KeyPair not recovered", e);
                        } catch (NoSuchAlgorithmException e) {
                            Log.w(TAG, "RSA not supported", e);
                        } catch (InvalidKeyException e) {
                            Log.w(TAG, "Invalid Key", e);
                        } catch (SignatureException e) {
                            Log.w(TAG, "Invalid Signature", e);
                        } catch (IOException e) {
                            Log.w(TAG, "IO Exception", e);
                        } catch (CertificateException e) {
                            Log.w(TAG, "Error occurred while loading certificates", e);
                        }finally {
                            Log.d(TAG, "Signature: " + mSignatureStr);
                        }
                        if(mSignatureStr != null){
                            SharedPreferences sharedPreferences = getActivity()
                                    .getSharedPreferences( SecurityConstants.SHARED_PERFERENCE, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(SecurityConstants.SIGNATURE,mSignatureStr);
                            editor.commit();
                            Toast.makeText(getContext(),"New Passcode Created",Toast.LENGTH_SHORT).show();
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container_login, new EnterFragment()).commit();
                        }else{
                            Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    public void createKeys(Context context) throws NoSuchProviderException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        Calendar start = new GregorianCalendar();
        Calendar end = new GregorianCalendar();
        end.add(Calendar.YEAR, 1);

        KeyPairGenerator kpGenerator = KeyPairGenerator
                .getInstance(SecurityConstants.TYPE_RSA,
                        SecurityConstants.KEYSTORE_PROVIDER_ANDROID_KEYSTORE);

        AlgorithmParameterSpec spec;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            spec = new KeyPairGeneratorSpec.Builder(context)
                    .setAlias(SecurityConstants.ALIAS)
                    .setSubject(new X500Principal("CN=" + SecurityConstants.ALIAS))
                    .setSerialNumber(BigInteger.valueOf(1337))
                    .setStartDate(start.getTime())
                    .setEndDate(end.getTime())
                    .build();


        } else {
            spec = new KeyGenParameterSpec.Builder(SecurityConstants.ALIAS, KeyProperties.PURPOSE_SIGN)
                    .setCertificateSubject(new X500Principal("CN=" + SecurityConstants.ALIAS))
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
                    .setCertificateSerialNumber(BigInteger.valueOf(1337))
                    .setCertificateNotBefore(start.getTime())
                    .setCertificateNotAfter(end.getTime())
                    .build();
        }

        kpGenerator.initialize(spec);

        KeyPair kp = kpGenerator.generateKeyPair();
        Log.d(TAG, "Public Key is: " + kp.getPublic().toString());
    }

    public String signData(String inputStr) throws KeyStoreException,
            UnrecoverableEntryException, NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, IOException, CertificateException {
        byte[] data = inputStr.getBytes();

        KeyStore ks = KeyStore.getInstance(SecurityConstants.KEYSTORE_PROVIDER_ANDROID_KEYSTORE);

        ks.load(null);

        KeyStore.Entry entry = ks.getEntry(SecurityConstants.ALIAS, null);

        if (entry == null) {
            Log.w(TAG, "No key found under alias: " + SecurityConstants.ALIAS);
            Log.w(TAG, "Exiting signData()...");
            return null;
        }

        if (!(entry instanceof KeyStore.PrivateKeyEntry)) {
            Log.w(TAG, "Not an instance of a PrivateKeyEntry");
            Log.w(TAG, "Exiting signData()...");
            return null;
        }
        Signature s = Signature.getInstance(SecurityConstants.SIGNATURE_SHA256withRSA);

        s.initSign(((KeyStore.PrivateKeyEntry) entry).getPrivateKey());

        s.update(data);
        byte[] signature = s.sign();
        String result = Base64.encodeToString(signature, Base64.DEFAULT);
        return result;
    }
}
