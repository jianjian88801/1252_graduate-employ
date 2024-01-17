<template>
    <div>
        <r-query-form :form="form" :items="items" @search="search"></r-query-form>
        <div class="btn-panel">
            <el-button size="small" type="primary" icon="el-icon-plus" @click="create">新增</el-button>
            <el-button size="small" type="primary" icon="el-icon-edit" @click="update">修改</el-button>
            <el-button size="small" type="danger" icon="el-icon-delete" @click="del">删除</el-button>
        </div>
        <r-table ref="mutipleTable" :tableData="tableData" :tableCols="tableCols">
            <template slot="slot_resume" slot-scope="scope">
                {{scope.data.resume.name}}
            </template>
        </r-table>
        <r-pagination :page="page" :total="total" @handleCurrentChange="handleCurrentChange"></r-pagination>
        <Add v-if="add.visible" :param="add"></Add>
        <Edit v-if="edit.visible" :param="edit"></Edit>
    </div>
</template>

<script>
    import {query} from "@/api/project";
    import Add from './Add'
    import Edit from "./Edit";
    import {message} from "@/utils/message";
    import {del} from '@/api/project'
    import RTable from "@/components/RTable";
    import RPagination from "@/components/RPagination";
    import RQueryForm from "../../components/RQueryForm";

    export default {
        name: "Project",
        components: {RQueryForm, RPagination, Edit, Add, RTable},
        data() {
            return {
                total: 0,
                page: 1,
                tableData: [],
                tableCols: [
                    {prop: 'id', label: 'ID', width: 80},
                    {prop: 'resumeId', label: '简历', slot: 'slot_resume'},
                    {prop: 'name', label: '项目名称'},
                    {prop: 'description', label: '项目描述'},
                    {prop: 'startDate', label: '开始时间'},
                    {prop: 'endDate', label: '结束时间'},
                ],
                form: {
                    name: '',
                },
                items: [
                    {type: 'text', label: '名称', name: 'name', placeholder: '按名称查询'},
                ],
                add: {
                    visible: false,
                    close: this.close,
                    callback: this.search
                },
                edit: {
                    visible: false,
                    close: this.close,
                    callback: this.search,
                    form: null,
                },
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
                console.log(params);
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
            close() {
                this.add.visible = false;
                this.edit.visible = false;
            },
            create() {
                this.add.visible = true;
            },
            update() {
                let selection = this.$refs['mutipleTable'].selection;
                if (selection.length == 1) {
                    this.edit.visible = true;
                    this.edit.form = selection[0]
                } else {
                    message.warning('请选择一条数据修改')
                }
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