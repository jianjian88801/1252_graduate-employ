<template>
    <el-dialog title="新增" :visible.sync="param.visible" @close="param.close" :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {create} from "@/api/train";
    import {query} from "@/api/resume";
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
                    resumeId: '',
                    startDate: '',
                    endDate: '',
                    company: '',
                    course: '',
                },
                rules: {
                    startDate: [{required: true, message: '必填项不能为空'}],
                    endDate: [{required: true, message: '必填项不能为空'}],
                    company: [{required: true, message: '必填项不能为空'}],
                    course: [{required: true, message: '必填项不能为空'}],
                },
                items: [
                    {type: 'select', label: '简历', prop: 'resumeId', name: 'resumeId', placeholder: '简历', options: []},
                    {type: 'date', label: '开始时间', prop: 'startDate', name: 'startDate', placeholder: '开始时间'},
                    {type: 'date', label: '结束时间', prop: 'endDate', name: 'endDate', placeholder: '结束时间'},
                    {type: 'text', label: '培训机构', prop: 'company', name: 'company', placeholder: '培训机构'},
                    {type: 'text', label: '培训课程', prop: 'course', name: 'course', placeholder: '培训课程'},
                ]
            }
        },
        mounted() {
            query({}).then(res => {
                let arr = [];
                res.data.forEach(item => {
                    let param = {value: item.id, label: item.name}
                    arr.push(param)
                })
                this.items[0].options = arr;
            });
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