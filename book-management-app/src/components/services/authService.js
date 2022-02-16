import axios from "axios";

const rootURL = "http://localhost:7071/users/";

class AuthService {
    login(username, password) {
        return axios
            .post(rootURL + "signin", {
                username,
                password
            })
            .then(response => {
                if(response.data.accessToken){
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });

    }

    logout() {
        localStorage.removeItem("user");
    }

    register(username, password){
        return axios.post(rootURL + "signup", {
            username,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
}

export default new AuthService();