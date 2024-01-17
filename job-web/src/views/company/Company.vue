<template>
    <div>
        <r-query-form :form="form" :items="items" @search="search"></r-query-form>
        <div class="btn-panel">
            <el-button size="small" type="danger" icon="el-icon-delete" @click="del">删除</el-button>
        </div>
        <r-table ref="mutipleTable" :tableData="tableData" :tableCols="tableCols">
        </r-table>
        <r-pagination :page="page" :total="total" @handleCurrentChange="handleCurrentChange"></r-pagination>
    </div>
</template>

<script>
    import {query, del} from "@/api/company";
    import RTable from "@/components/RTable";
    import RPagination from "@/components/RPagination";
    import {message} from "@/utils/message";
    import RQueryForm from "@/components/RQueryForm";

    export default {
        name: "Company",
        components: {RQueryForm, RPagination, RTable},
        data() {
            return {
                total: 0,
                page: 1,
                tableData: [],
                tableCols: [
                    {prop: 'id', label: 'ID', width: 80},
                    {prop: 'name', label: '名称'},
                    {prop: 'type', label: '类型'},
                    {prop: 'contact', label: '联系人'},
                    {prop: 'telephone', label: '联系电话'},
                    {prop: 'email', label: '邮箱'},
                    {prop: 'addr', label: '地址'},
                    {prop: 'size', label: '规模'},
                ],
                form: {
                    name: '',
                    contact: ''
                },
                items: [
                    {type: 'text', label: '名称', name: 'name', placeholder: '按名称查询'},
                    {type: 'text', label: '联系人', name: 'contact', placeholder: '按联系人查询'},
                ],
            }
        },
        mounted() {
            this.list({})
        },
        methods: {
            handleCurrentChange(val) {
                this.page = val;
                let params = this.form;
                params.page = this.page
                this.list(params);
            },
            search() {
                this.list(this.form);
            },
            list(params) {
                query(params).then(res => {
                    this.tableData = res.data;
                    this.total = res.total;
                })
            },
            del() {
                let selection = this.$refs['mutipleTable'].selection;
                if (selection.length > 0) {
                    this.$confirm('确定要删除吗', '删除提示').then(() => {
                        let arr = selection.map(item => item.id);
                        let ids = arr.join(',');
                        del(ids).then(res => {
                            message.success(res.msg);
                            this.search()
                        })
                    }).catch(() => {
                    })
                } else {
                    message.warning('请选择要删除的数据')
                }
            }
        }
    }
</script>

<style scoped>

</style>