package khoavin.sillylearningenglish.SYSTEM.Constant;

import static khoavin.sillylearningenglish.NetworkService.Retrofit.ApiUntils.BASE_URL;

/**
 * Created by KhoaVin on 1/19/2017.
 */

public class WebAddress {
    public static final String CHECK_LESSON_WAS_BOUGHT = BASE_URL + "training/check_lesson_was_bought";

    //region inbox
    public static final String MAIL_GET_ITEMS = BASE_URL + "/inbox/get_inbox";
    public static final String MAIL_DELETE = BASE_URL + "/inbox/remove_mail";
    public static final String MAIL_RATE = BASE_URL + "/inbox/rate";
    public static final String MAIL_CLAIM = BASE_URL + "/inbox/claim_reward";
    public static final String MAIL_MASK_OPENED = BASE_URL + "/inbox/mask_as_opened";
    //endregion

    //region battle
    public static final String BATTLE_GET_ENEMY_DUEL = BASE_URL + "/arena/get_enemy_duel";
    public static final String BATTLE_RESULT = BASE_URL + "/arena/get_battle_result";
    public static final String BATTLE_FIND = BASE_URL + "/arena/find_battle";
    public static final String BATTLE_GET_CHAINS = BASE_URL + "/arena/get_battle_chains";
    public static final String BATTLE_ACCEPT = BASE_URL + "/arena/accept_battle";
    public static final String BATTLE_CHOSE_ANSWER = BASE_URL + "/arena/chose_answer";
    public static final String BATTLE_CANCEL = BASE_URL + "/arena/cancel_battle";
    public static final String BATTLE_CREATE = BASE_URL + "/arena/create_battle";

    //endregion

    //region user
    public static final String USER_GET_OR_CREATE = BASE_URL + "/user/getinfo";
    //endregion
}

