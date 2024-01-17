<template>
    <el-dialog title="新增" :fullscreen="true" :visible.sync="param.visible" @close="param.close"
               :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {create} from "@/api/resume";
    import RForm from "@/components/RForm";

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
                    jobStatus: '',
                    evaluate: '',
                    skill: '',
                    status: 1,
                },
                rules: {
                    name: [{required: true, message: '必填项不能为空'}],
                    jobStatus: [{required: true, message: '必填项不能为空'}],
                    evaluate: [{required: true, message: '必填项不能为空'}],
                    skill: [{required: true, message: '必填项不能为空'}],
                    status: [{required: true, message: '必填项不能为空'}],
                },
                items: [
                    {type: 'text', label: '名称', prop: 'name', name: 'name', placeholder: '名称'},
                    {
                        type: 'select', label: '求职状态', prop: 'jobStatus', name: 'jobStatus', placeholder: '求职状态',
                        options: [{value: '待业可以立即上岗', label: '待业可以立即上岗'}, {value: '在岗考虑新工作', label: '在岗考虑新工作'}]
                    },
                    {type: 'area', label: '自我评价', prop: 'evaluate', name: 'evaluate', placeholder: '自我评价'},
                    {type: 'area', label: '技能描述', prop: 'skill', name: 'skill', placeholder: '技能描述'},
                    {
                        type: 'select', label: '状态', prop: 'status', name: 'status', placeholder: '状态',
                        options: [{value: 1, label: '开放'}, {value: 0, label: '关闭'}]
                    },
                ]
            }
        },
        methods: {
            save() {
                let flag = this.$refs['myForm'].validateForm();
                if (flag) {
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