package cn.com.liu.disruptor;

public class LongEvent {
	private long value;
	private String name;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringBuilder("名称：").append(this.name).append(" , value:").append(this.value).toString();
	}
}
