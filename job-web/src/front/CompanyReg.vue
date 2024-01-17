<template>
    <div style="width: 500px;margin: auto">
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </div>
</template>

<script>
    import RForm from "../components/RForm";
    import {company_create} from "@/api/front";

    export default {
        name: "CompanyReg",
        components: {RForm},
        data() {
            let validatePwd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.form.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            }
            return {
                form: {
                    name: '',
                    account: null,
                    password: '',
                    password2: '',
                    contact: '',
                    telephone: '',
                },
                rules: {
                    name: [{required: true, message: '必填项不能为空'}],
                    account: [{required: true, message: '必填项不能为空'}],
                    password: [{required: true, message: '必填项不能为空'}],
                    password2: [
                        {required: true, message: '必填项不能为空'},
                        {required: true, validator: validatePwd}
                    ],
                    phone: [{required: true, message: '必填项不能为空'}],
                },
                items: [
                    {type: 'text', label: '姓名', prop: 'name', name: 'name', placeholder: '请输入姓名'},
                    {type: 'text', label: '账号', prop: 'account', name: 'account', placeholder: '账号'},
                    {type: 'password', label: '密码', prop: 'password', name: 'password', placeholder: '密码'},
                    {type: 'password', label: '确认密码', prop: 'password2', name: 'password2', placeholder: '确认密码'},
                    {type: 'text', label: '联系人', prop: 'contact', name: 'contact', placeholder: '联系人'},
                    {type: 'text', label: '联系电话', prop: 'telephone', name: 'telephone', placeholder: '联系电话'},
                ]
            }
        },
        methods: {
            save() {
                let flag = this.$refs['myForm'].validateForm();
                if (flag) {
                    let param = this.form;
                    company_create(param).then(res => {
                        this.$message.success(res.msg);
                    })
                }
            }
        }

    }
</script>

<style scoped>

</style>