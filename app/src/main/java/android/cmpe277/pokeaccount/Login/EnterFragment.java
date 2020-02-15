package android.cmpe277.pokeaccount.Login;

import android.cmpe277.pokeaccount.MainActivity;
import android.cmpe277.pokeaccount.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public class EnterFragment extends LoginBasicFragment {
    public static final String TAG = "KeyStoreFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        final TextView loginText = view.findViewById(R.id.text_login);
        TextView loginTextBtn = view.findViewById(R.id.text_login_btn);

        loginText.setText("Welcome!");
        loginTextBtn.setText("Reset Passcode");

        loginTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_login,new CheckFragment()).commit();
            }
        });

        getPinView().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginText.setText("Welcome!");
                loginText.setTextColor(getResources().getColor(R.color.quantum_white_100));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean verified = false;
                if(charSequence.length() == LENGTH){
                    try {
                        verified = verifyData(charSequence.toString());
                    } catch (KeyStoreException e) {
                        Log.w(TAG, "KeyStore not Initialized", e);
                    } catch (CertificateException e) {
                        Log.w(TAG, "Error occurred while loading certificates", e);
                    } catch (NoSuchAlgorithmException e) {
                        Log.w(TAG, "RSA not supported", e);
                    } catch (IOException e) {
                        Log.w(TAG, "IO Exception", e);
                    } catch (UnrecoverableEntryException e) {
                        Log.w(TAG, "KeyPair not recovered", e);
                    } catch (InvalidKeyException e) {
                        Log.w(TAG, "Invalid Key", e);
                    } catch (SignatureException e) {
                        Log.w(TAG, "Invalid Signature", e);
                    }
                    if (verified) {
                        Log.d(TAG, "Data Signature Verified");
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                    } else {
                        pinView.setText("");
                        loginText.setText("Wrong Passcode");
                        loginText.setTextColor(getResources().getColor(R.color.red));
                        Log.d(TAG, "Data not verified.");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    public boolean verifyData(String input) throws KeyStoreException,
            CertificateException, NoSuchAlgorithmException, IOException,
            UnrecoverableEntryException, InvalidKeyException, SignatureException {
        byte[] data = input.getBytes();
        byte[] signature;

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences( SecurityConstants.SHARED_PERFERENCE, Context.MODE_PRIVATE);
        String signatureStr = sharedPreferences.getString(SecurityConstants.SIGNATURE, "");

        if (signatureStr == null) {
            Log.w(TAG, "Invalid signature.");
            Log.w(TAG, "Exiting verifyData()...");
            return false;
        }else{
            Log.w(TAG, "Signature: "+ signatureStr);
        }

        try {
            signature = Base64.decode(signatureStr, Base64.DEFAULT);
        } catch (IllegalArgumentException e) {
            return false;
        }

        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");

        ks.load(null);

        KeyStore.Entry entry = ks.getEntry(SecurityConstants.ALIAS, null);

        if (entry == null) {
            Log.w(TAG, "No key found under alias: " + SecurityConstants.ALIAS);
            Log.w(TAG, "Exiting verifyData()...");
            return false;
        }

        if (!(entry instanceof KeyStore.PrivateKeyEntry)) {
            Log.w(TAG, "Not an instance of a PrivateKeyEntry");
            return false;
        }

        Signature s = Signature.getInstance(SecurityConstants.SIGNATURE_SHA256withRSA);

        s.initVerify(((KeyStore.PrivateKeyEntry) entry).getCertificate());
        s.update(data);
        return s.verify(signature);
    }

}
