<template>
    <div>
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">

            <template v-for="(item,index) in items">
                <el-row :key="index">
                    <el-col :span="24">
                        <template v-if="['text','password','number','email'].indexOf(item.type) != -1">
                            <el-form-item :label="item.label" :prop="item.prop">
                                <el-input :type="item.type" v-model="form[item.name]"
                                          :placeholder="item.placeholder"></el-input>
                            </el-form-item>
                        </template>

                        <template v-if="['select'].indexOf(item.type) != -1">
                            <el-form-item :label="item.label" :prop="item.prop">
                                <el-select v-model="form[item.name]" :placeholder="item.placeholder">
                                    <el-option
                                            v-for="item in item.options"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </template>

                        <template v-if="['treeselect'].indexOf(item.type) != -1">
                            <el-form-item :label="item.label" :prop="item.prop">
                                <treeselect v-model="form[item.name]" :placeholder="item.placeholder"
                                            :options="item.options"/>
                            </el-form-item>
                        </template>

                        <template v-if="['switch'].indexOf(item.type) != -1">
                            <el-form-item :label="item.label" :prop="item.prop">
                                <el-switch v-model="form[item.name]" active-color="#13ce66"></el-switch>
                            </el-form-item>
                        </template>

                        <template v-if="['area'].indexOf(item.type) != -1">
                            <el-form-item :label="item.label" :prop="item.prop">
                                <mavon-editor v-model="form[item.name]"/>
                            </el-form-item>
                        </template>

                        <template v-if="['date'].indexOf(item.type) != -1">
                            <el-form-item :label="item.label" :prop="item.prop">
                                <el-date-picker
                                        v-model="form[item.name]"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="选择日期时间">
                                </el-date-picker>
                            </el-form-item>
                        </template>

                        <template v-if="['file'].indexOf(item.type) != -1">
                            <el-form-item :label="item.label" :prop="item.prop">
                                <el-upload
                                        class="upload-demo"
                                        action="string"
                                        :data="{name:item.name}"
                                        :http-request="uploadImg"
                                        :before-upload="beforeUpload"
                                        :limit="1">
                                    <el-button size="small" type="primary">点击上传</el-button>
                                    <div slot="tip" class="el-upload__tip">只能上传jpg/jpeg/png文件，且不超过2M</div>
                                </el-upload>
                            </el-form-item>
                        </template>
                    </el-col>
                </el-row>
            </template>
            <el-row>
                <el-form-item>
                    <el-button size="small" icon="el-icon-refresh-right" @click="resetField" type="primary">重置
                    </el-button>
                    <el-button size="small" icon="el-icon-s-claim" @click="save" type="primary">保存</el-button>
                </el-form-item>
            </el-row>
        </el-form>
    </div>
</template>

<script>
    // import the component
    import Treeselect from '@riophae/vue-treeselect'
    // import the styles
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'

    import {upload} from "../api/upload";

    import Vue from 'vue'
    import mavonEditor from 'mavon-editor'
    import 'mavon-editor/dist/css/index.css'
    // use
    Vue.use(mavonEditor)

    export default {
        name: "RForm",
        components: {Treeselect},
        props: {
            rules: {
                type: Object,
                default: () => {
                }
            },
            form: {
                type: Object,
                default: () => {
                }
            },
            save: {
                type: Function,
                default: () => {
                }
            },
            items: {
                type: Array,
                default: () => {
                }
            }
        },
        data() {
            return {
                uploadUrl: ''
            }
        },
        methods: {
            validateForm() {
                let flag = false;
                this.$refs['form'].validate(valid => {
                    if (valid) {
                        flag = true;
                    }
                })
                return flag;
            },
            resetField() {
                this.$refs['form'].resetFields();
            },
            uploadImg(param) {
                const formData = new FormData();
                formData.append("file", param.file);
                //调用文件上传
                upload(formData).then(res => {
                    this.form[param.data.name] = res.data;
                })
            },
            //上传图片之前的验证
            beforeUpload(file) {
                const isIMAGE = (file.type === 'image/jpeg') || (file.type === 'image/jpg') || (file.type === 'image/png');
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isIMAGE) {
                    this.$message.error('上传头像图片只能是 JPG/JPEG/PNG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isIMAGE && isLt2M;
            }
        }
    }
</script>

<style scoped>

</style>