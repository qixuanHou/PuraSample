package com.example.houqixuan.nov25;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;

import java.io.File;

/**
 * Created by houqixuan on 11/25/16.
*/

public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    public File getAlbumStorageDir(String albumName) {
        // TODO Auto-generated method stub
        return new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                ),
                albumName
        );
    }
}
