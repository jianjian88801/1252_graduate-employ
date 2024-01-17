import request from '@/utils/request';

export function create(data) {
    return request({
        url: 'article/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: 'article/update',
        method: 'post',
        data: data
    })
}

export function del(ids) {
    return request({
        url: 'article/delete',
        method: 'post',
        params: {"ids": ids}
    })
}

export function detail(id) {
    return request({
        url: 'article/detail',
        method: 'post',
        params: {"id": id}
    })
}

export function query(data) {
    return request({
        url: 'article/query',
        method: 'post',
        data: data
    })
}