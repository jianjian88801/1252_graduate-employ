<template>
    <el-card>
        <div slot="header" class="clearfix">
            <span>{{channelName}}</span>
        </div>
        <ul class="hot">
            <li v-for="(item,index) in articles.slice(0,7)" :key="index">
                <router-link :to="`/detail/${item.id}`" target="_blank">
                    {{item.title}}
                </router-link>
            </li>
        </ul>
    </el-card>
</template>

<script>
    import {article_query} from "../api/front";
    import {channel_detail} from "../api/front";

    export default {
        name: "RCard",
        props: ['channelId'],
        data() {
            return {
                channelName: '',
                articles: []
            }
        },
        mounted() {
            this.getChannel()
            this.getArticles()
        },
        methods: {
            getChannel() {
                channel_detail(this.channelId).then(res => {
                    this.channelName = res.data.name;
                })
            },
            getArticles() {
                article_query({channelId:this.channelId}).then(res => {
                    this.articles = res.data;
                })
            }
        }
    }
</script>

<style scoped>

</style>