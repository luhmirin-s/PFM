package main.client;
/**
 * Contains representations of all classes that require refreshing data
 */
public enum RefreshingClasses {
	ACC_MGR, CAT_MGR, SRC_MGR,
	TRA_EXP, TRA_INC, TRA_TRANSF, //global
	TRA_EXP_ACC, TRA_EXP_CUR, TRA_EXP_CAT //specific (handle received data)
}
