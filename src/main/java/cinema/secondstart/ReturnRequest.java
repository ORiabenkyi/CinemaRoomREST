package cinema.secondstart;

public class ReturnRequest {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ReturnRequest{" +
                "token=" + token +
                '}';
    }
}
