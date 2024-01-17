import request from '@/utils/request';

export function create(data) {
    return request({
        url: 'resume/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'resume/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'resume/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'resume/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function query(data) {
    return request({
        url: 'resume/query',
        method: 'post',
        data: data
    })
}