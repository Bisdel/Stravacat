package fr.formation.api.response;

public class ConnexionResponse {
	private AnimalResponse animalResponse;
	private boolean success;
	private String token;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AnimalResponse getAnimalResponse() {
		return animalResponse;
	}

	public void setAnimalResponse(AnimalResponse animalResponse) {
		this.animalResponse = animalResponse;
	}
}
