<template>
    <div v-title :data-title="channel.name">
        <Header></Header>
        <div class="main mar-20">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a href="/">{{channel.name}}</a></el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- 焦点区 -->
        <div class="main mar-20">
            <el-row :gutter="15">
                <el-col :span="16">
                    <el-card style="min-height: 1000px">
                        <div slot="header" class="clearfix">
                            <span>文章列表</span>
                        </div>
                        <ul class="list">
                            <li v-for="(item,index) in articles.slice(0,8)" :key="index">
                                <router-link :to="`/detail/${item.id}`" target="_blank">
                                    {{item.title}}
                                </router-link>
                                <p>{{item.summary}}</p>
                            </li>
                        </ul>
                        <el-pagination
                                background
                                layout="prev, pager, next"
                                :current-page="page"
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
        <Footer></Footer>
    </div>
</template>

<script>
    import '../assets/css/styles.css'
    import Header from "./Header";
    import {article_query, channel_detail} from "../api/front";
    import RCard from "./RCard";
    import Footer from "./Footer";

    export default {
        name: "Index",
        components: {Footer, Header, RCard},
        data() {
            return {
                channelId: '',
                value: new Date(),
                articles: [],
                channel: {},
                page: 1,
                total: 0
            }
        },
        mounted() {
            this.getArticles({channelId:this.$route.params.id});
            this.getChannel(this.$route.params.id);
        },
        beforeRouteUpdate (to, form, next) {
            this.page = 1;
            this.getArticles({channelId:to.params.id,page:this.page});
            this.getChannel(to.params.id);
            next();
        },
        methods: {
            getArticles(param) {
                article_query(param).then(res => {
                    this.articles = res.data;
                    this.total = res.total;
                })
            },
            getChannel(channelId) {
                channel_detail(channelId).then(res => {
                    this.channel = res.data;
                })
            },
            handleCurrentChange(val) {
                this.page = val;
                let params = {channelId:this.channel.id,page:this.page};
                this.getArticles(params);
            },
        }
    }
</script>

<style scoped>

</style>