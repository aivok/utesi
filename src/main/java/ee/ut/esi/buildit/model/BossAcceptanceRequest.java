package ee.ut.esi.buildit.model;

public abstract class BossAcceptanceRequest {
	private final int id;
	private static int id_;

	protected BossAcceptanceRequest() {
		this.id = id_++;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof BossAcceptanceRequest && ((BossAcceptanceRequest) obj).getId() == id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
