#ifndef __X10_ARRAY_MATBUILDER_H
#define __X10_ARRAY_MATBUILDER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(T)> class ArrayList;
} } 
namespace x10 { namespace array { 
class Row;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class VarRow;
} } 
namespace x10 { namespace array { 

class MatBuilder : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::ArrayList<x10aux::ref<x10::array::Row> > > FMGL(mat);
    
    x10_int FMGL(cols);
    
    void _constructor(x10_int cols);
    
    static x10aux::ref<x10::array::MatBuilder> _make(x10_int cols);
    
    void _constructor(x10_int rows, x10_int cols);
    
    static x10aux::ref<x10::array::MatBuilder> _make(x10_int rows, x10_int cols);
    
    virtual void add(x10aux::ref<x10::array::Row> row);
    virtual void add(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > a);
    virtual x10_int __apply(x10_int i, x10_int j);
    virtual void __set(x10_int v, x10_int i, x10_int j);
    virtual void setDiagonal(x10_int i, x10_int j, x10_int n, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > v);
    virtual void setColumn(x10_int i, x10_int j, x10_int n, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > v);
    virtual void setRow(x10_int i, x10_int j, x10_int n, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > v);
    void need(x10_int n);
    static void need(x10_int n, x10aux::ref<x10::util::ArrayList<x10aux::ref<x10::array::Row> > > mat,
                     x10_int cols);
    virtual x10aux::ref<x10::array::MatBuilder> x10__array__MatBuilder____x10__array__MatBuilder__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_MATBUILDER_H

namespace x10 { namespace array { 
class MatBuilder;
} } 

#ifndef X10_ARRAY_MATBUILDER_H_NODEPS
#define X10_ARRAY_MATBUILDER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/ArrayList.h>
#include <x10/array/Row.h>
#include <x10/lang/Int.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/VarRow.h>
#ifndef X10_ARRAY_MATBUILDER_H_GENERICS
#define X10_ARRAY_MATBUILDER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::MatBuilder::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::MatBuilder> this_ = new (memset(x10aux::alloc<x10::array::MatBuilder>(), 0, sizeof(x10::array::MatBuilder))) x10::array::MatBuilder();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_MATBUILDER_H_GENERICS
#endif // __X10_ARRAY_MATBUILDER_H_NODEPS
