<template>
    <el-dialog title="修改" :visible.sync="param.visible" @close="param.close" :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {update} from "@/api/certificate";
    import {query} from "@/api/resume";
    import RForm from "@/components/RForm";

    export default {
        name: "Edit",
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
                    id: '',
                    resumeId: '',
                    name: '',
                    file: '',
                    obtainDate: '',
                },
                rules: {
                    resumeId: [{required: true, message: '必填项不能为空'}],
                    name: [{required: true, message: '必填项不能为空'}]
                },
                items: [
                    {type: 'select', label: '简历', prop: 'resumeId', name: 'resumeId', placeholder: '简历', options: []},
                    {type: 'text', label: '证书名称', prop: 'name', name: 'name', placeholder: '公司名称'},
                    {type: 'file', label: '附件', prop: 'file', name: 'file', placeholder: '附件'},
                    {type: 'date', label: '获得时间', prop: 'obtainDate', name: 'obtainDate', placeholder: '获得时间'},
                ]
            }
        },
        mounted() {
            this.form = JSON.parse(JSON.stringify(this.param.form));
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
                    update(this.form).then(res => {
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