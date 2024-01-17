let local = {

    setToken(token) {
        localStorage.setItem("token", token);
    },

    getToken() {
        return localStorage.getItem("token");
    },

    setUserInfo(userInfo) {
        localStorage.setItem("userInfo", JSON.stringify(userInfo));
    },

    getUserInfo() {
        if (localStorage.getItem("userInfo")) {
            return JSON.parse(localStorage.getItem("userInfo"));
        } else {
            return null;
        }
    },

    remove() {
        localStorage.removeItem("token");
        localStorage.removeItem("userInfo");
    }
}

export default local