<template>
    <div>
        <!-- 顶部 -->
        <div class="top">
            <div class="main">
                <img src="../assets/images/logo-title.png" width="500px">
                <span class="reg-login" v-if="!loginState">
                    <router-link to="/register">注册</router-link>
                    <span> | </span>
                    <router-link to="/login">登录</router-link>
                </span>
                <span class="reg-login" v-else>
                    <router-link to="/index">管理中心</router-link>
                </span>
            </div>
        </div>
        <!-- 导航栏 -->
        <div class="nav">
            <div class="main">
                <ul>
                    <li>
                        <el-dropdown trigger="hover" :show-timeout="0">
                            <router-link to="/">
                                <span class="el-dropdown-link">主页</span>
                            </router-link>
                            <el-dropdown-menu slot="dropdown"></el-dropdown-menu>
                        </el-dropdown>
                    </li>
                    <li v-for="(item,index) in channels" :key="index">
                        <el-dropdown trigger="hover" :show-timeout="0">
                            <router-link :to="`/list/${item.id}`">
                                <span class="el-dropdown-link">{{item.label}}</span>
                            </router-link>
                            <el-dropdown-menu slot="dropdown">
                                <div v-for="(child,i) in item.children" v-bind:key="i">
                                    <el-dropdown-item>
                                        <router-link class="nav-sub" :to="`/list/${child.id}`">
                                            <span class="nav-sub">{{child.label}}</span>
                                        </router-link>
                                    </el-dropdown-item>
                                </div>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </li>
                    <li>
                        <el-dropdown :show-timeout="0">
                            <router-link to="/post_list">
                                <span class="el-dropdown-link">职位搜索</span>
                            </router-link>
                            <el-dropdown-menu slot="dropdown"></el-dropdown-menu>
                        </el-dropdown>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
    import {channel_tree} from "../api/front";
    import local from "../store/local";

    export default {
        name: "Header",
        data() {
            return {
                channels: []
            }
        },
        mounted() {
            this.getChannels();
        },
        computed: {
            loginState() {
                if(local.getToken()) {
                    return true;
                } else {
                    return false;
                }
            }
        },
        methods: {
            getChannels() {
                channel_tree({}).then(res => {
                    this.channels = res.data;
                });
            }
        }
    }
</script>

<style scoped>
    .reg-login {
        float: right;
        padding: 20px 20px;
        font-size: 14px;
    }
    .reg-login a {
        color: #333333;
        text-decoration: none;
    }
    .reg-login a:hover {
        color: red;
    }
</style>