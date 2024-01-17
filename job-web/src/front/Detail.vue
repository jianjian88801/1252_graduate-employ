<template>
    <div v-title :data-title="article.title">
        <Header></Header>
        <div class="main mar-20">
            <el-breadcrumb>
                <el-breadcrumb-item :to="{path:'/'}">首页</el-breadcrumb-item>
                <el-breadcrumb-item>
                    <router-link :to="`/list/${channel.id}`">{{channel.name}}</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item>正文</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <!-- 焦点区 -->
        <div class="main mar-20">
            <el-row :gutter="15">
                <el-col :span="16">
                    <el-card style="min-height: 1000px">
                        <div slot="header" class="clearfix">
                            <span>文章正文</span>
                        </div>
                        <div class="content">
                            <div class="title">{{article.title}}</div>
                            <div class="date">
                                <span>发布时间：{{article.createDate}}</span>
                                <span>作者：{{article.author}}</span>
                                <span>浏览量：{{article.views}}</span>
                            </div>
                            <div class="txt">
                                <mavon-editor :value="article.content"
                                              :toolbarsFlag="false"
                                              :subfield="false"
                                              :shortCut="false"
                                              :autofocus="false"
                                              :ishljs="true"
                                              :boxShadow="false"
                                              fontSize="16px"
                                              previewBackground="#FFFFFF"
                                              defaultOpen="preview" />
                            </div>
                        </div>
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
    import Footer from "./Footer";
    import {article_detail,channel_detail} from "../api/front";
    import RCard from "./RCard";

    import Vue from 'vue'
    import mavonEditor from 'mavon-editor'
    import 'mavon-editor/dist/css/index.css'
    // use
    Vue.use(mavonEditor)

    export default {
        name: "Index",
        components: {Footer, Header, RCard},
        data() {
            return {
                article: {},
                channel: {}
            }
        },
        mounted() {
            this.getArticle(this.$route.params.id);
        },
        methods: {
            getArticle(id) {
                article_detail(id).then(res => {
                    this.article = res.data;
                    channel_detail(this.article.channelId).then(res => {
                        this.channel = res.data;
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>