<template>
    <el-tabs v-model="activeName">
        <el-tab-pane :label="channelName1" name="first">
            <ul class="hot">
                <li v-for="(item,index) in articles1" :key="index">
                    <router-link :to="`/detail/${item.id}`" target="_blank">
                        {{item.title}}
                    </router-link>
                </li>
            </ul>
        </el-tab-pane>
        <el-tab-pane :label="channelName2" name="second">
            <ul class="hot">
                <li v-for="(item,index) in articles2" :key="index">
                    <router-link :to="`/detail/${item.id}`" target="_blank">
                        {{item.title}}
                    </router-link>
                </li>
            </ul>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import {article_query} from "../api/front";
    import {channel_detail} from "../api/front";

    export default {
        name: "RTab",
        props: ['ids'],
        data() {
            return {
                activeName: 'first',
                channelName1: '',
                channelName2: '',
                articles1: [],
                articles2: [],
            }
        },
        mounted() {
            this.getChannel();
            this.getArticles();
            this.getChannel2();
            this.getArticles2();
        },
        methods: {
            getChannel() {
                channel_detail(this.ids[0]).then(res => {
                    this.channelName1 = res.data.name;
                })
            },
            getArticles() {
                article_query({channelId: this.ids[0]}).then(res => {
                    this.articles1 = res.data;
                })
            },
            getChannel2() {
                channel_detail(this.ids[1]).then(res => {
                    this.channelName2 = res.data.name;
                })
            },
            getArticles2() {
                article_query({channelId: this.ids[1]}).then(res => {
                    this.articles2 = res.data;
                })
            }
        }
    }
</script>

<style scoped>

</style>