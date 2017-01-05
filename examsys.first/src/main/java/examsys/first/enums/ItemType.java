package examsys.first.enums;

public enum ItemType {
	SINGLE("单选", 1), MUTI("多选", 2), JUDGE("判断", 3), INPUT("客观", 4);
	
    private String name;
    private int value;
    private ItemType( String name , int value ){
        this.name = name ;
        this.value = value ;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
