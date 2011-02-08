#ifndef __X10_ARRAY_VARROW_H
#define __X10_ARRAY_VARROW_H

#include <x10rt.h>


#define X10_ARRAY_ROW_H_NODEPS
#include <x10/array/Row.h>
#undef X10_ARRAY_ROW_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 

class VarRow : public x10::array::Row   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Fun_0_1<x10_int, x10_int>::itable<x10::array::VarRow > _itable_0;
    
    static x10::lang::Any::itable<x10::array::VarRow > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Rail<x10_int > > FMGL(row);
    
    void _constructor(x10_int cols, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > init);
    
    static x10aux::ref<x10::array::VarRow> _make(x10_int cols, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > init);
    
    void _constructor(x10_int cols);
    
    static x10aux::ref<x10::array::VarRow> _make(x10_int cols);
    
    virtual x10aux::ref<x10::lang::Rail<x10_int > > row();
    virtual x10_int __apply(x10_int i);
    virtual x10_int __set(x10_int v, x10_int i);
    virtual x10aux::ref<x10::array::VarRow> x10__array__VarRow____x10__array__VarRow__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_VARROW_H

namespace x10 { namespace array { 
class VarRow;
} } 

#ifndef X10_ARRAY_VARROW_H_NODEPS
#define X10_ARRAY_VARROW_H_NODEPS
#include <x10/array/Row.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Int.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Rail.h>
#ifndef X10_ARRAY_VARROW_H_GENERICS
#define X10_ARRAY_VARROW_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::VarRow::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::VarRow> this_ = new (memset(x10aux::alloc<x10::array::VarRow>(), 0, sizeof(x10::array::VarRow))) x10::array::VarRow();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_VARROW_H_GENERICS
#endif // __X10_ARRAY_VARROW_H_NODEPS
