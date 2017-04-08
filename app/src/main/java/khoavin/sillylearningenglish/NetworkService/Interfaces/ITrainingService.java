package khoavin.sillylearningenglish.NetworkService.Interfaces;

import khoavin.sillylearningenglish.NetworkService.NetworkModels.LessonUnits;
import khoavin.sillylearningenglish.NetworkService.NetworkModels.Lessons;
import khoavin.sillylearningenglish.NetworkService.Retrofit.IServerResponse;

/**
 * Created by Khoavin on 4/2/2017.
 */

public interface ITrainingService {
    void GetPopularLesson(final IServerResponse<Lessons> receiver);
    void BuyLesson();
    void GetLessonUnit(int ls_id,final IServerResponse<LessonUnits> receiver);
}