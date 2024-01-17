import request from '@/utils/request';

export function create(data) {
    return request({
        url: 'user/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'user/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'user/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'user/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function query(data) {
    return request({
        url: 'user/query',
        method: 'post',
        data: data
    })
}