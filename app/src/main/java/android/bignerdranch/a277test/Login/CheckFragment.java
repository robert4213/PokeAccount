package android.bignerdranch.a277test.Login;

import android.bignerdranch.a277test.R;
import android.content.Context;
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

public class CheckFragment extends LoginBasicFragment {
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

        loginText.setText("Please Enter Your Old Passcode");
        loginTextBtn.setText("Cancel");

        loginTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ADD NEW Fragment
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_login,new EnterFragment()).commit();
            }
        });

        getPinView().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginText.setText("Please Enter Your Old Passcode");
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
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container_login, new NewPasscodeFragment()).commit();
                        Log.d(TAG, "Data Signature Verified");
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
        // BEGIN_INCLUDE(decode_signature)

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences( SecurityConstants.SHARED_PERFERENCE, Context.MODE_PRIVATE);
        String signatureStr = sharedPreferences.getString(SecurityConstants.SIGNATURE, "");
        // Make sure the signature string exists.  If not, bail out, nothing to do.

        if (signatureStr == null) {
            Log.w(TAG, "Invalid signature.");
            Log.w(TAG, "Exiting verifyData()...");
            return false;
        }

        try {
            // The signature is going to be examined as a byte array,
            // not as a base64 encoded string.
            signature = Base64.decode(signatureStr, Base64.DEFAULT);
        } catch (IllegalArgumentException e) {
            // signatureStr wasn't null, but might not have been encoded properly.
            // It's not a valid Base64 string.
            return false;
        }
        // END_INCLUDE(decode_signature)

        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");

        // Weird artifact of Java API.  If you don't have an InputStream to load, you still need
        // to call "load", or it'll crash.
        ks.load(null);

        // Load the key pair from the Android Key Store
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

        // This class doesn't actually represent the signature,
        // just the engine for creating/verifying signatures, using
        // the specified algorithm.
        Signature s = Signature.getInstance(SecurityConstants.SIGNATURE_SHA256withRSA);

        // BEGIN_INCLUDE(verify_data)
        // Verify the data.
        s.initVerify(((KeyStore.PrivateKeyEntry) entry).getCertificate());
        s.update(data);
        return s.verify(signature);
        // END_INCLUDE(verify_data)
    }
}
