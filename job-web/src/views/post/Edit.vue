<template>
    <el-dialog title="修改" :fullscreen="true" :visible.sync="param.visible" @close="param.close"
               :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {update} from "@/api/post";
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
                    name: '',
                    education: '',
                    salary: '',
                    description: '',
                },
                rules: {
                    name: [{required: true, message: '必填项不能为空'}],
                    education: [{required: true, message: '必填项不能为空'}],
                    salary: [{required: true, message: '必填项不能为空'}],
                },
                items: [
                    {type: 'text', label: '职位名称', prop: 'name', name: 'name', placeholder: '职位名称'},
                    {type: 'text', label: '学历要求', prop: 'education', name: 'education', placeholder: '学历要求'},
                    {type: 'text', label: '职位薪水', prop: 'salary', name: 'salary', placeholder: '职位薪水'},
                    {type: 'area', label: '职位描述', prop: 'description', name: 'description', placeholder: '职位描述'},
                ]
            }
        },
        mounted() {
            this.form = JSON.parse(JSON.stringify(this.param.form));
            this.getDictList1(2);
            this.getDictList2(3);
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
                        if (item.type == 'select' && item.name == 'education') {
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