#ifndef __X10_UTIL_HASHMAP__HASHENTRY_H
#define __X10_UTIL_HASHMAP__HASHENTRY_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_UTIL_MAP__ENTRY_H_NODEPS
#include <x10/util/Map__Entry.h>
#undef X10_UTIL_MAP__ENTRY_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 

template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
template <> class HashMap__HashEntry<void, void>;
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::util::Map__Entry<FMGL(Key), FMGL(Value)>::template itable<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > _itable_1;
    
    void _instance_init();
    
    virtual FMGL(Key) getKey();
    virtual FMGL(Value) getValue();
    virtual void setValue(FMGL(Value) v);
    FMGL(Key) FMGL(key);
    
    FMGL(Value) FMGL(value);
    
    x10_boolean FMGL(removed);
    
    x10_int FMGL(hash);
    
    void _constructor(FMGL(Key) key, FMGL(Value) value, x10_int h);
    
    static x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > _make(
             FMGL(Key) key,
             FMGL(Value) value,
             x10_int h);
    
    virtual x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >
      x10__util__HashMap__HashEntry____x10__util__HashMap__HashEntry__this(
      );
    void __fieldInitializers2099();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class HashMap__HashEntry<void, void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_HASHMAP__HASHENTRY_H

namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)>
class HashMap__HashEntry;
} } 

#ifndef X10_UTIL_HASHMAP__HASHENTRY_H_NODEPS
#define X10_UTIL_HASHMAP__HASHENTRY_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/Map__Entry.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#ifndef X10_UTIL_HASHMAP__HASHENTRY_H_GENERICS
#define X10_UTIL_HASHMAP__HASHENTRY_H_GENERICS
template<class FMGL(Key), class FMGL(Value)> template<class __T> x10aux::ref<__T> x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >(), 0, sizeof(x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>))) x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_HASHMAP__HASHENTRY_H_GENERICS
#ifndef X10_UTIL_HASHMAP__HASHENTRY_H_IMPLEMENTATION
#define X10_UTIL_HASHMAP__HASHENTRY_H_IMPLEMENTATION
#include <x10/util/HashMap__HashEntry.h>


template<class FMGL(Key), class FMGL(Value)> typename x10::util::Map__Entry<FMGL(Key), FMGL(Value)>::template itable<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >  x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_itable_0(&x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::equals, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::getKey, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::getValue, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::hashCode, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::setValue, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::toString, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::typeName);
template<class FMGL(Key), class FMGL(Value)> x10::lang::Any::itable<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >  x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_itable_1(&x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::equals, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::hashCode, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::toString, &x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::typeName);
template<class FMGL(Key), class FMGL(Value)> x10aux::itable_entry x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::util::Map__Entry<FMGL(Key), FMGL(Value)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >())};
template<class FMGL(Key), class FMGL(Value)> void x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>");
    
}


//#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)> FMGL(Key) x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::getKey(
  ) {
    
    //#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
             FMGL(key);
    
}

//#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)> FMGL(Value) x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::getValue(
  ) {
    
    //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
             FMGL(value);
    
}

//#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
void
  x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::setValue(
  FMGL(Value) v) {
    
    //#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(value) = v;
}

//#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c

//#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c

//#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c

//#line 28 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c

//#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(Key), class FMGL(Value)>
void x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_constructor(
  FMGL(Key) key,
  FMGL(Value) value,
  x10_int h)
{
    this->::x10::lang::Object::_constructor();
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::__fieldInitializers2099();
    
    //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(key) =
      key;
    
    //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(value) =
      value;
    
    //#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(hash) =
      h;
    
    //#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(removed) =
      false;
    
}
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_make(
  FMGL(Key) key,
  FMGL(Value) value,
  x10_int h)
{
    x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >(), 0, sizeof(x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>))) x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>();
    this_->_constructor(key,
    value,
    h);
    return this_;
}



//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >
  x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::x10__util__HashMap__HashEntry____x10__util__HashMap__HashEntry__this(
  ) {
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this);
    
}

//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
void
  x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::__fieldInitializers2099(
  ) {
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(removed) = false;
}
template<class FMGL(Key), class FMGL(Value)>
const x10aux::serialization_id_t x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(Key), class FMGL(Value)>
void x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(key));
    buf.write(this->FMGL(value));
    buf.write(this->FMGL(removed));
    buf.write(this->FMGL(hash));
    
}

template<class FMGL(Key), class FMGL(Value)>
void x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(key) = buf.read<FMGL(Key)>();
    FMGL(value) = buf.read<FMGL(Value)>();
    FMGL(removed) = buf.read<x10_boolean>();
    FMGL(hash) = buf.read<x10_int>();
}

template<class FMGL(Key), class FMGL(Value)>
x10aux::RuntimeType x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::rtt;
template<class FMGL(Key), class FMGL(Value)>
void x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::HashMap__HashEntry<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::util::Map__Entry<FMGL(Key), FMGL(Value)> >()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(Key)>(), x10aux::getRTT<FMGL(Value)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.HashMap.HashEntry";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 2, params, variances);
}
#endif // X10_UTIL_HASHMAP__HASHENTRY_H_IMPLEMENTATION
#endif // __X10_UTIL_HASHMAP__HASHENTRY_H_NODEPS
