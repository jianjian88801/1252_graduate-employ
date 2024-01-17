<template>
    <div>
        <Header></Header>
        <!-- 焦点区 -->
        <div class="main mar-20">
            <el-row :gutter="15">
                <el-col :span="16">
                    <el-card>
                        <r-swiper></r-swiper>
                    </el-card>
                </el-col>
                <el-col :span="8">
                    <el-card>
                        <r-tab :ids="[11,17]"></r-tab>
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <!-- 二层区域 -->
        <div class="main mar-20">
            <el-row :gutter="15">
                <el-col :span="8">
                    <r-card :channelId="18"></r-card>
                </el-col>
                <el-col :span="8">
                    <r-card :channelId="21"></r-card>
                </el-col>
                <el-col :span="8">
                    <el-card>
                        <el-calendar v-model="value"></el-calendar>
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <!-- 广告图片区域 -->
        <div class="main mar-20">
            <img src="../assets/images/middle.png" width="1200px">
        </div>
        <!-- 三层区域 -->
        <div class="main mar-20">
            <el-row :gutter="15">
                <el-col :span="8">
                    <r-card :channelId="22"></r-card>
                </el-col>
                <el-col :span="8">
                    <r-card :channelId="30"></r-card>
                </el-col>
                <el-col :span="8">
                    <r-card :channelId="16"></r-card>
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
    import RCard from "./RCard";
    import RTab from "./RTab";
    import RSwiper from "./RSwiper";

    export default {
        name: "Index",
        components: {RCard, Header, Footer, RTab, RSwiper},
        data() {
            return {
                value: new Date(),
                swiperOption: {
                    pagination: {
                        el: '.swiper-pagination'
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>
