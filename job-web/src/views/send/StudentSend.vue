<template>
    <div>
        <div class="btn-panel">
            <el-button size="small" type="danger" icon="el-icon-delete" @click="del">删除</el-button>
        </div>
        <r-table ref="mutipleTable" :tableData="tableData" :tableCols="tableCols">
            <template slot="slot_post" slot-scope="scope">
                {{scope.data.post.name}}
            </template>
            <template slot="slot_company" slot-scope="scope">
                {{scope.data.company.name}}
            </template>
            <template slot="slot_resume" slot-scope="scope">
                {{scope.data.resume.name}}
            </template>
            <template slot="slot_status" slot-scope="scope">
                <el-tag v-if="scope.data.status == 0">待查看</el-tag>
                <el-tag v-if="scope.data.status == 1">已查看</el-tag>
                <el-tag v-if="scope.data.status == 2">有意向</el-tag>
                <el-tag v-if="scope.data.status == 3">不合适</el-tag>
            </template>
        </r-table>
        <r-pagination :page="page" :total="total" @handleCurrentChange="handleCurrentChange"></r-pagination>
    </div>
</template>

<script>
    import {student_send} from "@/api/send";
    import {message} from "@/utils/message";
    import {del} from '@/api/send'
    import RTable from "@/components/RTable";
    import RPagination from "@/components/RPagination";

    export default {
        name: "StudentSend",
        components: {RPagination, RTable},
        data() {
            return {
                total: 0,
                page: 1,
                tableData: [],
                tableCols: [
                    {prop: 'id', label: 'ID', width: 80},
                    {prop: 'postId', label: '职位', slot: 'slot_post'},
                    {prop: 'companyId', label: '公司', slot: 'slot_company'},
                    {prop: 'resumeId', label: '简历名称', slot: 'slot_resume'},
                    {prop: 'status', label: '状态', slot: 'slot_status'},
                    {prop: 'sendDate', label: '投递日期'},
                ],
            }
        },
        mounted() {
            this.list({})
        },
        methods: {
            handleCurrentChange(val) {
                this.page = val;
                let params = {};
                params.page = this.page
                this.list(params);
            },
            list(params) {
                student_send(params).then(res => {
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
                            this.list({})
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