/*
 * Copyright (c) 2016.
 *
 * DReflect and Minuku Libraries by Shriti Raj (shritir@umich.edu) and Neeraj Kumar(neerajk@uci.edu) is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 * Based on a work at https://github.com/Shriti-UCI/Minuku-2.
 *
 *
 * You are free to (only if you meet the terms mentioned below) :
 *
 * Share — copy and redistribute the material in any medium or format
 * Adapt — remix, transform, and build upon the material
 *
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 *
 * Under the following terms:
 *
 * Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.
 * NonCommercial — You may not use the material for commercial purposes.
 * ShareAlike — If you remix, transform, or build upon the material, you must distribute your contributions under the same license as the original.
 * No additional restrictions — You may not apply legal terms or technological measures that legally restrict others from doing anything the license permits.
 */

package edu.umich.si.inteco.minuku_2.action;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import edu.umich.si.inteco.minuku.config.Constants;
import edu.umich.si.inteco.minuku.logger.Log;
import edu.umich.si.inteco.minuku_2.QuestionnaireActivity;
import edu.umich.si.inteco.minuku_2.R;
import edu.umich.si.inteco.minuku_2.event.MoodAnnotationExpectedActionEvent;
import edu.umich.si.inteco.minuku_2.preferences.ApplicationConstants;
import edu.umich.si.inteco.minuku_2.question.QuestionConfig;
import edu.umich.si.inteco.minukucore.event.ShowNotificationEvent;
import edu.umich.si.inteco.minukucore.event.ShowNotificationEventBuilder;

/**
 * Created by shriti on 8/2/16.
 */
public class MoodAnnotationExpectedAction {

    private static final String TAG = "MoodAnnotnExpctdAction";

    public MoodAnnotationExpectedAction() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void handleMoodAnnotationExpected(MoodAnnotationExpectedActionEvent missedDataActionEvent) {
        Log.d(TAG, "Handling state change event for mood data record");

        Map<String, String> dataSentToQuestinnaireActivity = new HashMap<>();
        dataSentToQuestinnaireActivity.put(Constants.BUNDLE_KEY_FOR_QUESTIONNAIRE_ID,
                String.valueOf(QuestionConfig.moodChangeQuestionnaire.getID()));

        EventBus.getDefault().post(
                new ShowNotificationEventBuilder()
                        .setExpirationAction(ShowNotificationEvent.ExpirationAction.DISMISS)
                        .setExpirationTimeSeconds(Constants.MOOD_NOTIFICATION_EXPIRATION_TIME)
                        .setViewToShow(QuestionnaireActivity.class)
                        .setIconID(R.drawable.self_reflection)
                        .setTitle(Constants.MOOD_ANNOTATION_TITLE)
                        .setMessage(Constants.MOOD_ANNOTATION_MESSAGE)
                        .setCategory(ApplicationConstants.NOTIFICATION_CATEGORY_MOOD_ANNOTATION)
                        .setParams(dataSentToQuestinnaireActivity)
                        .createShowNotificationEvent()
        );
    }
}
