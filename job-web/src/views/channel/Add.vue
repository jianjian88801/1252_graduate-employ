<template>
    <el-dialog title="新增" :fullscreen="true" :visible.sync="param.visible" @close="param.close"
               :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {create, tree} from "../../api/channel";
    import RForm from "../../components/RForm";

    export default {
        name: "Add",
        components: {RForm},
        props: {
            param: {
                type: Object,
                default: () => {
                }
            }
        },
        data() {
            return {
                form: {
                    name: '',
                    parentId: null,
                    channelImg: '',
                    summary: '',
                    single: 'N',
                    url: '',
                    content: '',
                    sort: '',
                    postion: ''
                },
                rules: {
                    name: [{required: true, message: "必填项不能为空"}]
                },
                items: [
                    {type: 'text', label: '栏目名称', prop: 'name', name: 'name', placeholder: '请录入栏目名称'},
                    {
                        type: 'treeselect', label: '上级栏目', prop: 'parentId', name: 'parentId', placeholder: '请选择上级栏目',
                        options: [],
                    },
                    {type: 'file', label: '栏目图片', prop: 'channelImg', name: 'channelImg', placeholder: '请上传图片'},
                    {type: 'text', label: '摘要', prop: 'summary', name: 'summary', placeholder: '请录入摘要'},
                    {type: 'switch', label: '单页', prop: 'single', name: 'single', placeholder: '请选择单页'},
                    {type: 'text', label: '外链URL', prop: 'url', name: 'url', placeholder: '请录入外链'},
                    {type: 'number', label: '排序', prop: 'sort', name: 'sort', placeholder: '请录入排序号'},
                    {type: 'number', label: '位置', prop: 'postion', name: 'postion', placeholder: '请录入位置'},
                    {type: 'area', label: '内容', prop: 'content', name: 'content', placeholder: '请录入内容'}
                ]
            }
        },
        mounted() {
            this.channel_tree();
        },
        methods: {
            channel_tree() {
                tree().then(res => {
                    this.items.forEach(item => {
                        if (item.type == 'treeselect' && item.name == 'parentId') {
                            item.options = res.data;
                        }
                    })
                });
            },
            save() {
                let flag = this.$refs['myForm'].validateForm();
                if (flag) {
                    if (this.form.single) {
                        this.form.single = 'Y';
                    } else {
                        this.form.single = 'N';
                    }
                    //执行保存
                    create(this.form).then(res => {
                        this.$message.success(res.msg);
                        this.param.close();
                        this.param.callback();
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>