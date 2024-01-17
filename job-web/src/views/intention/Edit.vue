<template>
    <el-dialog title="修改" :visible.sync="param.visible" @close="param.close" :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {update} from "@/api/intention";
    import {query} from "@/api/resume";
    import RForm from "@/components/RForm";
    import {dict_query} from "@/api/dict";

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
                    id: '',
                    resumeId: '',
                    name: '',
                    salary: '',
                    city: '',
                    type: '',
                    industry: '',
                },
                rules: {
                    resumeId: [{required: true, message: '必填项不能为空'}],
                    name: [{required: true, message: '必填项不能为空'}],
                    salary: [{required: true, message: '必填项不能为空'}],
                },
                items: [
                    {type: 'select', label: '简历', prop: 'resumeId', name: 'resumeId', placeholder: '简历', options: []},
                    {type: 'text', label: '职位名称', prop: 'name', name: 'name', placeholder: '职位名称'},
                    {type: 'select', label: '薪资', prop: 'salary', name: 'salary', placeholder: '薪资'},
                    {type: 'text', label: '工作城市', prop: 'city', name: 'city', placeholder: '城市'},
                    {type: 'select', label: '工作性质', prop: 'type', name: 'type', placeholder: '工作性质'},
                    {type: 'text', label: '期望行业', prop: 'industry', name: 'industry', placeholder: '期望行业'},
                ]
            }
        },
        mounted() {
            this.form = JSON.parse(JSON.stringify(this.param.form));
            this.getDictList1(3);
            this.getDictList2(6);

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
            getDictList2(typeId) {
                dict_query({typeId: typeId}).then(res => {
                    let arr = [];
                    res.data.forEach(item => {
                        let p = {value: item.dictKey, label: item.dictVal};
                        arr.push(p);
                    });
                    this.items.forEach(item => {
                        if (item.type == 'select' && item.name == 'type') {
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