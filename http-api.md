1. /user/login?name=testuser&password=password
    result = {
        status: 1, //1��ʾ��½�ɹ���0��ʾ�޴��û���-1��ʾ��½ʧ�ܣ�������������᷵����������
        uid: 1,
        name: "testuser",
        ..
    }

2. /view/item?iid=2
    result = {
        iid: 2,
        name: "testitem",
        fid: 3,  //��ʾ��Ʒ��������id
        description: "",
        availability: 1, //1��ʾ���Խ��ģ�0��ʾ���ɽ���
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