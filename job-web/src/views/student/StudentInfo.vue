<template>
    <div>
        <r-form ref="myForm" :rules="rules" :form="form" :items="items" :save="save"></r-form>
    </div>
</template>

<script>
    import RForm from "@/components/RForm";
    import {studentInfo, update} from '@/api/student'
    import {dict_query} from "@/api/dict";

    export default {
        name: "StudentInfo",
        components: {RForm},
        data() {
            return {
                form: {
                    id: '',
                    name: '',
                    birthday: '',
                    college: '',
                    education: '',
                    phone: '',
                    sex: '',
                    photo: '',
                    graduateDate: ''
                },
                rules: {
                    name: [{required: true, message: '必填项不能为空'}]
                },
                items: [
                    {type: 'text', label: '姓名', prop: 'name', name: 'name', placeholder: '请输入名称'},
                    {type: 'date', label: '出生日期', prop: 'birthday', name: 'birthday', placeholder: '出生日期'},
                    {type: 'text', label: '毕业院校', prop: 'college', name: 'college', placeholder: '毕业院校'},
                    {type: 'select', label: '学历', prop: 'education', name: 'education', placeholder: '学历', options: []},
                    {type: 'text', label: '电话', prop: 'phone', name: 'phone', placeholder: '电话'},
                    {type: 'select', label: '性别', prop: 'sex', name: 'sex', placeholder: '性别', options: []},
                    {type: 'file', label: '照片', prop: 'photo', name: 'photo', placeholder: '照片'},
                    {type: 'date', label: '毕业时间', prop: 'graduateDate', name: 'graduateDate', placeholder: '毕业时间'},
                ]
            }
        },
        mounted() {
            this.getDictList1(2);
            this.getDictList2(1);
            this.getInfo();
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
                        if (item.type == 'select' && item.name == 'sex') {
                            item.options = arr;
                        }
                    })
                })
            },
            getInfo() {
                studentInfo().then(res => {
                    this.form = res.data;
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