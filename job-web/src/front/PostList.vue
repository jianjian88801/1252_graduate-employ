<template>
    <div>
        <Header></Header>
        <!-- 焦点区 -->
        <div class="main mar-20">
            <el-row :gutter="15">
                <el-col :span="16">
                    <el-card style="min-height: 1000px">
                        <div slot="header" class="clearfix">
                            <span>职位搜索</span>
                        </div>
                        <ul class="post-list">
                            <li v-for="(item,index) in posts" :key="index">
                                <a href="#">
                                    {{item.name}}
                                    <span style="float: right">
                                        <el-button type="primary" @click="send(item.id, item.companyId)" size="mini" round>申请职位</el-button>
                                        <el-button type="danger"  @click="favor(item.id, item.companyId)" size="mini" round>收藏职位</el-button>
                                    </span>
                                </a>
                                <p>
                                    <span>{{item.salary}}</span>
                                    <span>{{item.type}}</span>
                                    <span>{{item.size}}</span>
                                    <span>{{item.companyName}}</span>
                                </p>
                            </li>
                        </ul>
                        <el-pagination
                                background
                                layout="prev, pager, next, total"
                                @current-change="handleCurrentChange"
                                :total="total">
                        </el-pagination>
                    </el-card>
                </el-col>
                <el-col :span="8">
                    <r-card :channelId="18"></r-card>
                </el-col>
            </el-row>
        </div>

        <!-- 底部区域 -->
        <Footer></Footer>
    </div>
</template>

<script>
    import '../assets/css/styles.css'
    import Header from "./Header";
    import Footer from "./Footer";
    import RCard from "./RCard";
    import {post_query} from "../api/front";
    import {send_create} from "../api/send";
    import {favor_create} from "../api/favor";
    import local from "../store/local";
    import {message} from "../utils/message";

    export default {
        name: "Index",
        props: ['channelId'],
        components: {RCard, Footer, Header},
        data() {
            return {
                posts: [],
                total: 0
            }
        },
        mounted() {
            this.getPostList();
        },
        methods: {
            handleCurrentChange(page) {
                //
            },
            getPostList() {
                post_query({}).then(res => {
                    this.posts = res.data;
                    this.total = res.total;
                })
            },
            send(postId, companyId) {
                if(local.getToken()) {
                    if(local.getUserInfo().type === 2) {
                        //执行申请
                        send_create({postId:postId, companyId:companyId}).then(res => {
                            this.$message.success(res.msg);
                        })
                    } else {
                        message.error("非学生登录不能申请，请以学生身份进行登录")
                    }
                } else {
                    message.error("请先登录");
                }
            },
            favor(postId, companyId) {
                if(local.getToken()) {
                    if(local.getUserInfo().type === 2) {
                        //执行收藏
                        favor_create({postId:postId, companyId:companyId}).then(res => {
                            this.$message.success(res.msg);
                        })
                    } else {
                        message.error("非学生登录不能收藏，请以学生身份进行登录")
                    }
                } else {
                    message.error("请先登录");
                }
            }
        }
    }
</script>

<style scoped>

</style>