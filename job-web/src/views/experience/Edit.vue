<template>
    <el-dialog title="修改" :visible.sync="param.visible" @close="param.close" :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {update} from "@/api/experience";
    import {query} from "@/api/resume";
    import RForm from "@/components/RForm";
    import {dict_query} from "@/api/dict";

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
                    company: '',
                    post: '',
                    salary: '',
                    description: '',
                    joinDate: '',
                    leaveDate: '',
                },
                rules: {
                    resumeId: [{required: true, message: '必填项不能为空'}],
                    company: [{required: true, message: '必填项不能为空'}],
                    post: [{required: true, message: '必填项不能为空'}],
                },
                items: [
                    {type: 'select', label: '简历', prop: 'resumeId', name: 'resumeId', placeholder: '简历', options: []},
                    {type: 'text', label: '公司名称', prop: 'company', name: 'company', placeholder: '公司名称'},
                    {type: 'text', label: '职位', prop: 'post', name: 'post', placeholder: '职位'},
                    {type: 'select', label: '薪水', prop: 'salary', name: 'salary', placeholder: '薪水'},
                    {type: 'text', label: '描述', prop: 'description', name: 'description', placeholder: '描述'},
                    {type: 'date', label: '入职时间', prop: 'joinDate', name: 'joinDate', placeholder: '入职时间'},
                    {type: 'date', label: '离职时间', prop: 'leaveDate', name: 'leaveDate', placeholder: '离职时间'},
                ]
            }
        },
        mounted() {
            this.form = JSON.parse(JSON.stringify(this.param.form));
            this.getDictList1(3);

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
            getDictList1(typeId) {
                dict_query({typeId: typeId}).then(res => {
                    let arr = [];
                    res.data.forEach(item => {
                        let p = {value: item.dictKey, label: item.dictVal};
                        arr.push(p);
                    });
                    this.items.forEach(item => {
                        if (item.type == 'select' && item.name == 'salary') {
                            item.options = arr;
                        }
                    })
                })
            },
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