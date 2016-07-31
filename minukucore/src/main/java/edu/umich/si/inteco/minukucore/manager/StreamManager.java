package edu.umich.si.inteco.minukucore.manager;

import java.util.List;

import edu.umich.si.inteco.minukucore.event.IsDataExpectedEvent;
import edu.umich.si.inteco.minukucore.event.NoDataChangeEvent;
import edu.umich.si.inteco.minukucore.event.StateChangeEvent;
import edu.umich.si.inteco.minukucore.event.Subscribe;
import edu.umich.si.inteco.minukucore.exception.StreamAlreadyExistsException;
import edu.umich.si.inteco.minukucore.exception.StreamNotFoundException;
import edu.umich.si.inteco.minukucore.model.DataRecord;
import edu.umich.si.inteco.minukucore.stream.Stream;
import edu.umich.si.inteco.minukucore.streamgenerator.StreamGenerator;

/**
 * Created by shriti on 7/9/16.
 * Registry of all streams in the system
 * Provides methods to access Streams
 * @see edu.umich.si.inteco.minukucore.stream.Stream
 * @version 1.0
 */
public interface StreamManager {

    /**
     * Fetch all the streams that are registered with the StreamManager
     * @return a list of Stream class instances {@link edu.umich.si.inteco.minukucore.stream.Stream}
     */
    public List<Stream> getAllStreams();

    /**
     * Register a Stream s with application specific DataRecord of type T
     * Registering a stream that does not exist throws exception
     * Registering a stream that is already registered throws exception
     *
     * @param s the new stream to be registered
     * @param clazz the class for DataRecord that the Stream holds
     * @param aStreamGenerator the StreamGenerator which called the register method.
     * @param <T> the application specific DataRecord {@link edu.umich.si.inteco.minukucore.model.DataRecord}
     * @throws StreamNotFoundException {@link edu.umich.si.inteco.minukucore.exception.StreamNotFoundException}
     * @throws StreamAlreadyExistsException {@link edu.umich.si.inteco.minukucore.exception.StreamAlreadyExistsException}
     */
    public <T extends DataRecord> void register(Stream s, Class<T> clazz,
                                                StreamGenerator aStreamGenerator)
            throws StreamNotFoundException, StreamAlreadyExistsException;

    /**
     * Unregister stream s
     * Unregistering a stream that does not exist throws expection
     * @param s the Stream to be unregistered
     * @param sg the StreamGenerator to be registered
     * @return null
     * @throws StreamNotFoundException {@link edu.umich.si.inteco.minukucore.exception.StreamNotFoundException}
     */
    public void unregister(Stream s, StreamGenerator sg) throws StreamNotFoundException;

    /**
     * Fetch the Stream for the DataRecord that the stream holds
     * Throw exception if the stream does not exist
     * @param clazz the class for DataRecord that the Stream holds
     * @param <T> the application specific DataRecord {@link edu.umich.si.inteco.minukucore.model.DataRecord}
     * @return a stream of application specific DataRecord T
     * @throws StreamNotFoundException {@link edu.umich.si.inteco.minukucore.exception.StreamNotFoundException}
     */
    public <T extends DataRecord> Stream<T> getStreamFor(Class<T> clazz) throws StreamNotFoundException;


    /**
     * Fetch the StreamGenerator registered for a DataRecord.
     * Throw exception of the stream related to this stream is not registered.
     * @param clazz the class for the DataRecord that is being generated by this StreamGenerator
     * @param <T> the application specific DataRecord
     * @return a stream generator for the application specific DataRecord <T>
     * @throws StreamNotFoundException
     */
    public <T extends DataRecord> StreamGenerator<T> getStreamGeneratorFor(Class<T> clazz)
            throws StreamNotFoundException;


    //subscribe this to the state change event in the abstract class

    /**
     * Called when a state change event is triggered by the StreamGenerator
     * {@link edu.umich.si.inteco.minukucore.streamgenerator.StreamGenerator}
     * Subscribes to the state change event
     * {@link edu.umich.si.inteco.minukucore.event.StateChangeEvent}
     * This method then calls the state change handler of the SituationManager
     * {@link edu.umich.si.inteco.minukucore.manager.SituationManager}
     */
    @Subscribe
    public void handleStateChangeEvent(StateChangeEvent aStateChangeEvent);

    @Subscribe
    public void handleNoDataChangeEvent(NoDataChangeEvent aNoDataChangeEvent);

    @Subscribe
    public void handleIsDAtaExpectedEvent(IsDataExpectedEvent aIsDataExpectedEvent);

    /**
     * get all the streams of a specific type
     * @param <T>        the application specific DataRecord
     *                   {@link DataRecord}
     * @param streamType type of stream
     *                   {@link Stream.StreamType}
     * @return           list of streams
     */
    public <T extends DataRecord> List<Stream<? extends DataRecord>> getStreams(
            Stream.StreamType streamType);

}

