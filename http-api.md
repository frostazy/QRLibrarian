1. /user/login?name=testuser&password=password
    result = {
        status: 1, //1表示登陆成功，0表示无此用户，-1表示登陆失败，后两种情况不会返回其他数据
        uid: 1,
        name: "testuser",
        ..
    }

2. /view/item?iid=2
    result = {
        iid: 2,
        name: "testitem",
        fid: 3,  //表示物品所属的域id
        description: "",
        availability: 1, //1表示可以借阅，0表示不可借阅
        url: ""
    }

3. /view/field?fid=3
    result = {
        available: [
            {
                iid: 1
                name: "testitem1"
            }, {
                iid: 2
                name: "testitem2"
            }, ..
        ],
        notAvailable: [
            {
                iid: 3
                name: "testitem3"
            }, {
                iid: 4
                name: "testitem4"
            }, ..
        ]
    }

4. /user/borrow?iid=1
    result = {
        status: 1, //
        uid: 1,
        name: "testuser",
        borrowItem: [
            {
                iid: 1
                name: "testitem1"
                fid: 1
                borrowTime: "2015-12-21 22:21:23"
            }, {
                iid: 2
                name: "testitem2"
                fid: 1
                borrowTime: "2015-12-21 22:21:23"
            }, ..
        ]
    }

5. /user/return?iid=1
    result = {
        status: 1, //
        uid: 1,
        name: "testuser",
        borrowItem: [
            {
                iid: 1
                name: testitem1"
                fid: 1
                borrowTime: "2015-12-21 22:21:23"
            }, {
                iid: 2
                name: "testitem2"
                fid: 1
                borrowTime: "2015-12-21 22:21:23"
            }, ..
        ]
    }