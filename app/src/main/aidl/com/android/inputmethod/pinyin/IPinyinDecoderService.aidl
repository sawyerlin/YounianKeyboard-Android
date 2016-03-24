// IPinyinDecoderService.aidl
package com.android.inputmethod.pinyin;

// Declare any non-default types here with import statements

interface IPinyinDecoderService {
    int getInt();
    void setMaxLens(int maxSpsLen, int maxHzsLen);
    void imResetSearch();
    int imDelSearch(int pos, boolean is_pos_in_splid,
                               boolean clear_fixed_this_step);
    int imSearch(in byte[] pyBuf, int pyLen);
    int imChoose(int choiceId);
    int[] imGetSplStart();
    String imGetPyStr(boolean decoded);
    int imGetPyStrLen(boolean decoded);
    String imGetChoice(int choiceId);
    String imGetChoices(int choicesNum);
    int imGetFixedLen();
    List<String> imGetChoiceList(int choicesStart, int choicesNum, int sentFixedLen);
    List<String> imGetPredictList(int predictsStart, int predictsNum);
    int imAddLetter(byte ch);
    int imCancelLastChoice();
    boolean imCancelInput();
    void imFlushCache();
    int imGetPredictsNum(String fixedStr);
    String imGetPredictItem(int predictNo);
    String syncUserDict(String tomerge);
    boolean syncBegin();
    void syncFinish();
    int syncPutLemmas(String tomerge);
    String syncGetLemmas();
    int syncGetLastCount();
    int syncGetTotalCount();
    void syncClearLastGot();
    int imSyncGetCapacity();
}
