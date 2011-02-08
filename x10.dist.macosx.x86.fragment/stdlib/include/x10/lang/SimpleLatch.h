#ifndef __X10_LANG_SIMPLELATCH_H
#define __X10_LANG_SIMPLELATCH_H

#include <x10rt.h>


#define X10_LANG_LOCK_H_NODEPS
#include <x10/lang/Lock.h>
#undef X10_LANG_LOCK_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Runtime__Worker;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 

class SimpleLatch : public x10::lang::Lock   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::io::CustomSerialization::itable<x10::lang::SimpleLatch > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::SimpleLatch > _itable_1;
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::lang::SimpleLatch> _make();
    
    virtual x10aux::ref<x10::io::SerialData> serialize();
    void _constructor(x10aux::ref<x10::io::SerialData> id__142);
    
    static x10aux::ref<x10::lang::SimpleLatch> _make(x10aux::ref<x10::io::SerialData> id__142);
    
    x10aux::ref<x10::lang::Runtime__Worker> FMGL(worker);
    
    x10_boolean FMGL(state);
    
    virtual void await();
    virtual void release();
    virtual x10_boolean __apply();
    virtual x10aux::ref<x10::lang::SimpleLatch> x10__lang__SimpleLatch____x10__lang__SimpleLatch__this(
      );
    void __fieldInitializers2032();
    
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
#endif // X10_LANG_SIMPLELATCH_H

namespace x10 { namespace lang { 
class SimpleLatch;
} } 

#ifndef X10_LANG_SIMPLELATCH_H_NODEPS
#define X10_LANG_SIMPLELATCH_H_NODEPS
#include <x10/lang/Lock.h>
#include <x10/io/SerialData.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/lang/String.h>
#include <x10/lang/Runtime__Worker.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Runtime.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_LANG_SIMPLELATCH_H_GENERICS
#define X10_LANG_SIMPLELATCH_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::SimpleLatch::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::SimpleLatch> this_ = new (memset(x10aux::alloc<x10::lang::SimpleLatch>(), 0, sizeof(x10::lang::SimpleLatch))) x10::lang::SimpleLatch();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_SIMPLELATCH_H_GENERICS
#endif // __X10_LANG_SIMPLELATCH_H_NODEPS
