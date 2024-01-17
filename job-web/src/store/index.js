import Vue from 'vue'
import Vuex from 'vuex'
import {login} from '@/api/login'
import local from "./local";
import {queryMenus} from "../api/menu";
import {userRouterMap} from '../router/user_router'
import {companyRouterMap} from '../router/company_router'
import {studentRouterMap} from '../router/student_router'
import router from "../router";
import {resetRouter} from "../router";

Vue.use(Vuex)

const index = new Vuex.Store({
    state: {
        token: null,
        userInfo: null
    },
    getters: {},
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token;
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
        }
    },
    actions: {
        login({commit}, loginUser) {
            return new Promise((resolve, reject) => {
                login(loginUser).then(res => {
                    //如果res存在的时候
                    if (res) {
                        //将token和用户数据存储到store
                        commit('SET_TOKEN', res.data.token);
                        commit('SET_USERINFO', res.data);
                        //将token和用户数据存入localStorage中
                        local.setToken(res.data.token);
                        local.setUserInfo(res.data);
                        resetRouter();
                        if(res.data.type == 0) {
                            router.addRoutes(userRouterMap);
                        }
                        if(res.data.type == 1) {
                            router.addRoutes(companyRouterMap);
                        }
                        if(res.data.type == 2) {
                            router.addRoutes(studentRouterMap);
                        }
                        //成功响应
                        resolve(res);
                    }
                }).catch(error => {
                    reject(error);
                })
            })
        },
        getMenus({commit}) {
            return new Promise((resolve, reject) => {
                queryMenus().then(res => {
                    resolve(res);
                })
            })
        }
    }
})

export default index