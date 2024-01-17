<template>
    <el-dialog title="新增" :visible.sync="param.visible" @close="param.close" :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {create} from "../../api/profession";
    import RForm from "../../components/RForm";
    import {query} from "../../api/profession";

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
                    parentId: ''
                },
                rules: {
                    name: [{required: true, message: "必填项不能为空"}],
                },
                items: [
                    {type: 'text', label: '行业类别', prop: 'name', name: 'name', placeholder: '请录入行业类别'},
                    {
                        type: 'select', label: '上级类别', prop: 'parentId', name: 'parentId', placeholder: '请录入上级名称',
                        options: []
                    },
                ]
            }
        },
        created() {
            query({}).then(res => {
                let arr = res.data.filter(item => item.parentId == 0);
                console.log(arr)
                arr.forEach(item => {
                    let param = {value: item.id, label: item.name};
                    this.items[1].options.push(param);
                })
            })
        },
        methods: {
            save() {
                let flag = this.$refs['myForm'].validateForm();
                if (flag) {
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