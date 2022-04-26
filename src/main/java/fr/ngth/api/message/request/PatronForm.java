package fr.ngth.api.message.request;

public class PatronForm {

	private String name;

	private String url;

	private String quality;
	
	private String numberJob;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	public String getNumberJob() {
		return numberJob;
	}

	public void setNumberJob(String numberJob) {
		this.numberJob = numberJob;
	}

}
