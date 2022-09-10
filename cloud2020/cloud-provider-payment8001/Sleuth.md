### zipkin 原理
##### Trace:类似于树结构的Span集合，表示一条调用链路，存在唯一标识
##### span:表示调用链路来源，通俗的理解span就是一次请求信息
##### 一条链路通过Trace Id唯一标识，Span标识发起的请求信息，各span通过parent id 关联起来
##### **eg**:{TraceId:X,SpanId:A,parentId:null}->{TraceId:X,SpanId:B,parentId:A}->{TraceId:X,SpanId:C,parentId:B}
