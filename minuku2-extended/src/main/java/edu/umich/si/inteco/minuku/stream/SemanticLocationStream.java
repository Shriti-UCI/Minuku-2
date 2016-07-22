package edu.umich.si.inteco.minuku.stream;

import android.location.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.umich.si.inteco.minuku.model.LocationDataRecord;
import edu.umich.si.inteco.minuku.model.SemanticLocationDataRecord;
import edu.umich.si.inteco.minukucore.model.DataRecord;
import edu.umich.si.inteco.minukucore.stream.AbstractStreamFromDevice;

/**
 * Created by neerajkumar on 7/21/16.
 */
public class SemanticLocationStream extends AbstractStreamFromDevice<SemanticLocationDataRecord> {

    public SemanticLocationStream(int aMaxSize) {
        super(aMaxSize);
    }

    @Override
    public List<Class<? extends DataRecord>> dependsOnDataRecord() {
        List<Class<? extends DataRecord>> dependsOn = new ArrayList<>();
        dependsOn.add(LocationDataRecord.class);
        return dependsOn;
    }
}