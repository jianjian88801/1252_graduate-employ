<template>
    <el-dialog title="修改" :visible.sync="param.visible" @close="param.close" :close-on-click-modal="false">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </el-dialog>
</template>

<script>
    import {update} from "../../api/dict";
    import RForm from "../../components/RForm";

    export default {
        name: "Add",
        components: {RForm},
        props: {
            param: {
                type: Object,
                default: () => {
                }
            },
            typeId: {
                type: Number
            }
        },
        mounted() {
            this.form = JSON.parse(JSON.stringify(this.param.form))
        },
        data() {
            return {
                form: {
                    id: '',
                    typeId: this.typeId,
                    dictKey: '',
                    dictVal: '',
                    sort: 10,
                    status: 0
                },
                rules: {
                    dictKey: [{required: true, message: "必填项不能为空"}],
                    dictVal: [{required: true, message: "必填项不能为空"}],
                },
                items: [
                    {type: 'text', label: '键名称', prop: 'dictKey', name: 'dictKey', placeholder: '请录入字典名称'},
                    {type: 'text', label: '键值', prop: 'dictVal', name: 'dictVal', placeholder: '请录入字典值'},
                    {type: 'text', label: '序号', prop: 'sort', name: 'sort', placeholder: '请录入序号'},
                    {
                        type: 'select', label: '状态', prop: 'status', name: 'status', placeholder: '请选择状态',
                        options: [{value: 0, label: '正常'}, {value: 1, label: '删除'}]
                    },
                ]
            }
        },
        methods: {
            save() {
                let flag = this.$refs['myForm'].validateForm();
                if (flag) {
                    //执行保存
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