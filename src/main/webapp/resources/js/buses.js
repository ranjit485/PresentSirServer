let fv, offCanvasEl;
document.addEventListener("DOMContentLoaded", function(e) {
    var t;
    t = document.getElementById("form-add-new-record"),
    setTimeout( () => {
        let e = document.querySelector(".create-new")
          , t = document.querySelector("#add-new-record");
        e && e.addEventListener("click", function() {
            offCanvasEl = new bootstrap.Offcanvas(t),
            t.querySelector(".dt-full-name").value = "",
            t.querySelector(".dt-post").value = "",
            t.querySelector(".dt-email").value = "",
            t.querySelector(".dt-date").value = "",
            t.querySelector(".dt-salary").value = "",
            offCanvasEl.show()
        })
    }
    , 200),
    fv = FormValidation.formValidation(t, {
        fields: {
            basicFullname: {
                validators: {
                    notEmpty: {
                        message: "The name is required"
                    }
                }
            },
            basicPost: {
                validators: {
                    notEmpty: {
                        message: "Post field is required"
                    }
                }
            },
            basicEmail: {
                validators: {
                    notEmpty: {
                        message: "The Email is required"
                    },
                    emailAddress: {
                        message: "The value is not a valid email address"
                    }
                }
            },
            basicDate: {
                validators: {
                    notEmpty: {
                        message: "Joining Date is required"
                    },
                    date: {
                        format: "MM/DD/YYYY",
                        message: "The value is not a valid date"
                    }
                }
            },
            basicSalary: {
                validators: {
                    notEmpty: {
                        message: "Basic Salary is required"
                    }
                }
            }
        },
        plugins: {
            trigger: new FormValidation.plugins.Trigger,
            bootstrap5: new FormValidation.plugins.Bootstrap5({
                eleValidClass: "",
                rowSelector: ".col-sm-12"
            }),
            submitButton: new FormValidation.plugins.SubmitButton,
            autoFocus: new FormValidation.plugins.AutoFocus
        },
        init: e => {
            e.on("plugins.message.placed", function(e) {
                e.element.parentElement.classList.contains("input-group") && e.element.parentElement.insertAdjacentElement("afterend", e.messageElement)
            })
        }
    }),
    (t = document.querySelector('[name="basicDate"]')) && t.flatpickr({
        enableTime: !1,
        dateFormat: "m/d/Y",
        onChange: function() {
            fv.revalidateField("basicDate")
        }
    })
}),
$(function() {
    var l, t, e = $(".datatables-basic"), a = $(".dt-complex-header"), s = $(".dt-row-grouping"), n = $(".dt-multilingual"), r = (e.length && (l = e.DataTable({
        ajax: assetsPath+"json/buses.json",
        
        columnDefs: [{
            className: "control",
            orderable: !1,
            searchable: !1,
            responsivePriority: 2,
            targets: 0,
            render: function(e, t, a, s) {
                return ""
            }
        }, {
            targets: 1,
            searchable: !1,
            visible: !1
        }, {
            targets:    2,
            responsivePriority: 4,
            render: function(e, t, a, s) {
                    l = a.route
                  , r = a.id;
                return '<div class="d-flex justify-content-start align-items-center user-name"><div class="avatar-wrapper"><div class="avatar me-2">' + (n ? '<i class="bx bx-bus bx-md"></i>' : '<span class="avatar-initial rounded-circle bg-label-' + ["success", "danger", "warning", "info", "dark", "primary", "secondary"][Math.floor(6 * Math.random())] + '">' + (n = (((n = (l = a.route).match(/\b\w/g) || []).shift() || "") + (n.pop() || "")).toUpperCase()) + "</span>") + '</div></div><div class="d-flex flex-column"><span class="emp_name text-truncate">' + l + '</span><small class="emp_post text-truncate text-muted">' + r + "</small></div></div>"
            }
        }, {
            responsivePriority: 1,
            targets: 3
        }, {
            targets: -1,
            title: "Actions",
            orderable: !1,
            searchable: !1,
            render: function(e, t, a, s) {
                return '<div class="d-inline-block"><a href="javascript:;" class="btn btn-icon dropdown-toggle hide-arrow me-1" ><i class="bx bx-edit bx-md"></i></a></div><a href="javascript:;" class="btn btn-icon item-edit"><i class="bx bx-trash bx-md"></i></a>'
            }
        }],
        order: [[2, "desc"]],
        dom: '<"card-header flex-column flex-md-row pb-0"<"head-label text-center"><"dt-action-buttons text-end pt-6 pt-md-0"B>><"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6 d-flex justify-content-center justify-content-md-end mt-n6 mt-md-0"f>>t<"row"<"col-sm-12 col-md-6"i><"col-sm-12 col-md-6"p>>',
        displayLength: 7,
        lengthMenu: [7, 10, 25, 50, 75, 100],
        language: {
            paginate: {
                next: '<i class="bx bx-chevron-right bx-18px"></i>',
                previous: '<i class="bx bx-chevron-left bx-18px"></i>'
            }
        },
        buttons: [{
            extend: "collection",
            className: "btn btn-label-primary dropdown-toggle me-4",
            text: '<i class="bx bx-export bx-sm me-sm-2"></i> <span class="d-none d-sm-inline-block">Export</span>',
            buttons: [{
                extend: "print",
                text: '<i class="bx bx-printer me-1" ></i>Print',
                className: "dropdown-item",
                exportOptions: {
                    columns: [3, 4, 5, 6, 7],
                    format: {
                        body: function(e, t, a) {
                            var s;
                            return e.length <= 0 ? e : (e = $.parseHTML(e),
                            s = "",
                            $.each(e, function(e, t) {
                                void 0 !== t.classList && t.classList.contains("user-name") ? s += t.lastChild.firstChild.textContent : void 0 === t.innerText ? s += t.textContent : s += t.innerText
                            }),
                            s)
                        }
                    }
                },
                customize: function(e) {
                    $(e.document.body).css("color", config.colors.headingColor).css("border-color", config.colors.borderColor).css("background-color", config.colors.bodyBg),
                    $(e.document.body).find("table").addClass("compact").css("color", "inherit").css("border-color", "inherit").css("background-color", "inherit")
                }
            }, {
                extend: "csv",
                text: '<i class="bx bx-file me-1" ></i>Csv',
                className: "dropdown-item",
                exportOptions: {
                    columns: [3, 4, 5, 6, 7],
                    format: {
                        body: function(e, t, a) {
                            var s;
                            return e.length <= 0 ? e : (e = $.parseHTML(e),
                            s = "",
                            $.each(e, function(e, t) {
                                void 0 !== t.classList && t.classList.contains("user-name") ? s += t.lastChild.firstChild.textContent : void 0 === t.innerText ? s += t.textContent : s += t.innerText
                            }),
                            s)
                        }
                    }
                }
            }, {
                extend: "excel",
                text: '<i class="bx bxs-file-export me-1"></i>Excel',
                className: "dropdown-item",
                exportOptions: {
                    columns: [3, 4, 5, 6, 7],
                    format: {
                        body: function(e, t, a) {
                            var s;
                            return e.length <= 0 ? e : (e = $.parseHTML(e),
                            s = "",
                            $.each(e, function(e, t) {
                                void 0 !== t.classList && t.classList.contains("user-name") ? s += t.lastChild.firstChild.textContent : void 0 === t.innerText ? s += t.textContent : s += t.innerText
                            }),
                            s)
                        }
                    }
                }
            }, {
                extend: "pdf",
                text: '<i class="bx bxs-file-pdf me-1"></i>Pdf',
                className: "dropdown-item",
                exportOptions: {
                    columns: [3, 4, 5, 6, 7],
                    format: {
                        body: function(e, t, a) {
                            var s;
                            return e.length <= 0 ? e : (e = $.parseHTML(e),
                            s = "",
                            $.each(e, function(e, t) {
                                void 0 !== t.classList && t.classList.contains("user-name") ? s += t.lastChild.firstChild.textContent : void 0 === t.innerText ? s += t.textContent : s += t.innerText
                            }),
                            s)
                        }
                    }
                }
            }, {
                extend: "copy",
                text: '<i class="bx bx-copy me-1" ></i>Copy',
                className: "dropdown-item",
                exportOptions: {
                    columns: [3, 4, 5, 6, 7],
                    format: {
                        body: function(e, t, a) {
                            var s;
                            return e.length <= 0 ? e : (e = $.parseHTML(e),
                            s = "",
                            $.each(e, function(e, t) {
                                void 0 !== t.classList && t.classList.contains("user-name") ? s += t.lastChild.firstChild.textContent : void 0 === t.innerText ? s += t.textContent : s += t.innerText
                            }),
                            s)
                        }
                    }
                }
            }]
        }, {
            text: '<i class="bx bx-plus bx-sm me-sm-2"></i> <span class="d-none d-sm-inline-block">Add New Record</span>',
            className: "create-new btn btn-primary"
        }],
        responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal({
                    header: function(e) {
                        return "Details of " + e.data().full_name
                    }
                }),
                type: "column",
                renderer: function(e, t, a) {
                    a = $.map(a, function(e, t) {
                        return "" !== e.title ? '<tr data-dt-row="' + e.rowIndex + '" data-dt-column="' + e.columnIndex + '"><td>' + e.title + ":</td> <td>" + e.data + "</td></tr>" : ""
                    }).join("");
                    return !!a && $('<table class="table"/><tbody />').append(a)
                }
            }
        }
    }),
    $("div.head-label").html('<h5 class="card-title mb-0">DataTable with Buttons</h5>'),
    $(".dt-buttons > .btn-group > button").removeClass("btn-secondary")),
    101);
    fv.on("core.form.valid", function() {
        var e = $(".add-new-record .dt-full-name").val()
          , t = $(".add-new-record .dt-post").val()
          , a = $(".add-new-record .dt-email").val()
          , s = $(".add-new-record .dt-date").val()
          , n = $(".add-new-record .dt-salary").val();
        "" != e && (l.row.add({
            id: r,
            full_name: e,
            post: t,
            email: a,
            start_date: s,
            salary: "$" + n,
            status: 5
        }).draw(),
        r++,
        offCanvasEl.hide())
    }),
        setTimeout( () => {
        $(".dataTables_filter .form-control").removeClass("form-control-sm"),
        $(".dataTables_length .form-select").removeClass("form-select-sm")
    }
    , 300)
});
