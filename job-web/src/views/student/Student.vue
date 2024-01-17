<template>
    <div>
        <r-query-form :form="form" :items="items" @search="search"></r-query-form>
        <div class="btn-panel">
            <el-button size="small" type="danger" icon="el-icon-delete" @click="del">删除</el-button>
        </div>

        <r-table ref="mutipleTable" :tableData="tableData" :tableCols="tableCols">
            <template slot="sex_slot" slot-scope="scope">
                <span v-if="scope.data.sex==0">女</span>
                <span v-else>男</span>
            </template>
        </r-table>
        <r-pagination :page="page" :total="total" @handleCurrentChange="handleCurrentChange"></r-pagination>
    </div>
</template>

<script>
    import {query, del} from "@/api/student";
    import RTable from "../../components/RTable";
    import RPagination from "../../components/RPagination";
    import RQueryForm from "../../components/RQueryForm";

    export default {
        name: "Student",
        components: {RQueryForm, RPagination, RTable},
        data() {
            return {
                tableData: [],
                tableCols: [
                    {prop: 'id', label: 'ID', width: 80},
                    {prop: 'name', label: '姓名'},
                    {prop: 'birthday', label: '出生日期'},
                    {prop: 'professionId', label: '专业'},
                    {prop: 'college', label: '学院'},
                    {prop: 'education', label: '学历'},
                    {prop: 'phone', label: '手机号'},
                    {prop: 'sex', label: '性别', slot: 'sex_slot'},
                    {prop: 'graduateDate', label: '毕业时间'},
                ],
                total: 0,
                page: 1,
                form: {
                    name: ''
                },
                items: [
                    {type: 'text', label: '姓名', name: 'name', placeholder: '按姓名查询'},
                ],
            }
        },
        mounted() {
            this.list({});
        },
        methods: {
            handleCurrentChange(val) {
                this.page = val;
                let params = this.form;
                params.page = this.page;
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
                let selections = this.$refs['mutipleTable'].selection;
                if (selections.length > 0) {
                    this.$confirm("确定有删除吗？", "删除提示").then(() => {
                        let arr = selections.map(item => item.id);
                        let ids = arr.join(',');
                        del(ids).then(res => {
                            this.$message.success(res.msg);
                            this.search();
                        })
                    }).catch(() => {
                    })
                } else {
                    this.$message.warning("请选择要删除的数据");
                }
            }
        }
    }
</script>

<style scoped>

</style>