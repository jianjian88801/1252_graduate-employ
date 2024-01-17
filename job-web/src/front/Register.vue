<template>
    <div>
        <Header></Header>
        <!-- 焦点区 -->
        <div class="main mar-20">
            <el-row :gutter="15">
                <el-col :span="16">
                    <el-card style="min-height: 1000px">
                        <el-tabs v-model="activeName">
                            <el-tab-pane label="学生注册" name="first">
                                <student-reg></student-reg>
                            </el-tab-pane>
                            <el-tab-pane label="企业注册" name="second">
                                <company-reg></company-reg>
                            </el-tab-pane>
                        </el-tabs>
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
    import StudentReg from "./StudentReg";
    import CompanyReg from "./CompanyReg";

    export default {
        name: "Index",
        props: ['channelId'],
        components: {RCard, Footer, Header, StudentReg, CompanyReg},
        data() {
            return {
                activeName: 'first',

            }
        }
    }
</script>

<style scoped>

</style>