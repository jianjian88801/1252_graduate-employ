import axios from "axios";
import local from "../store/local";
import index from "../router";
import {message} from "./message"

const axiosInstance = axios.create({
    baseURL: 'http://127.0.0.1:8888/job/',
    timeout: 10000
})

//配置请求拦截器
axiosInstance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    if (local.getToken()) {
        config.headers.token = local.getToken();
    }
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

//配置响应拦截器
axiosInstance.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    const res = response.data;
    if (response.status == 200) {
        if (res.code == 200) {
            return res;
        } else if (res.code == 401) {
            message.error(res.msg);
            index.push('/login')
            return Promise.reject(res.msg);
        } else if (res.code == 402) {
            message.error(res.msg);
            index.push('/login')
            return Promise.reject(res.msg);
        } else if (res.code == 500) {
            message.error(res.msg);
            return Promise.reject(res.msg);
        }
    }
    return response;
}, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
});

export default axiosInstance