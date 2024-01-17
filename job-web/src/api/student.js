import request from '@/utils/request';

export function create(data) {
    return request({
        url: 'student/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'student/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'student/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'student/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function query(data) {
    return request({
        url: 'student/query',
        method: 'post',
        data: data
    })
}

export function studentInfo(data) {
    return request({
        url: 'student/info',
        method: 'post',
        data: data
    })
}